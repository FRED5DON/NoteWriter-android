package kenel.app.sagosoft.com.Storage;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import kenel.app.sagosoft.com.Delegate.IRequester;


/**
 * Created by FRED_anjujia on 2015/12/1.
 */
public class LocalStorage {
    enum ErrCode {
        Err_No,
        Err_Request,
        Err_Exception
    }

    private static LocalStorage localStorage;

    private static LocalStorage build(Context context) {
        if (localStorage == null) {
            localStorage = new LocalStorage();
        }
        return localStorage;
    }


    public void readFile(String path, String fileName, IRequester iRequester) {
        File file = new File(path, fileName);
        if (file.exists() && !file.isDirectory()) {
            try {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                StringBuffer sb = new StringBuffer();
                String mimeTypeLine = null;
                while ((mimeTypeLine = br.readLine()) != null) {
                    sb.append(mimeTypeLine);
                }
                if (iRequester != null) {
                    iRequester.onSuccess(ErrCode.Err_No, sb.toString());
                }
            } catch (IOException e) {
                if (iRequester != null) {
                    iRequester.onFailure(ErrCode.Err_Exception, e.getMessage());
                }
            }

        }
    }

    /**
     * 待修复
     *
     * @param path
     * @param fileName
     * @param iRequester
     * @deprecated 存在句柄持有导致无法删除文件
     */
    public void readFileFast(String path, String fileName, IRequester iRequester) {
        File file = new File(path, fileName);
        if (file.exists() && !file.isDirectory()) {
            try {
                RandomAccessFile randomFile = new RandomAccessFile(path + File.separator + fileName, "r");
                FileChannel channel = randomFile.getChannel();
                long size = channel.size();
                MappedByteBuffer mbb = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
                StringBuffer sb = new StringBuffer();
                final int bufferSize = 8192;
                byte[] buffer = new byte[bufferSize];
                int bufferIndex = 0;
                for (int i = 0; i < size; i++) {
                    byte b = mbb.get(i);
                    if (bufferIndex == buffer.length) {
                        bufferIndex = 0;
                        //写出buffer
                        bytesTostring(sb, buffer, buffer.length);
                        buffer = new byte[bufferSize];
                    }
                    buffer[bufferIndex++] = b;
                }
                bytesTostring(sb, buffer, bufferIndex);

                if (iRequester != null) {
                    iRequester.onSuccess(ErrCode.Err_No, sb.toString());
                }
            } catch (IOException e) {
                if (iRequester != null) {
                    iRequester.onFailure(ErrCode.Err_Exception, e.getMessage());
                }
            }

        }
    }

    private void bytesTostring(StringBuffer sb, byte[] buffer, int len) {


    }


    /**
     * 追加文件内容
     *
     * @param path
     * @param fileName
     * @param content
     * @param iRequester
     */
    public void appendFile(String path, String fileName, String content, IRequester iRequester) {
        try {
            File file = new File(path, fileName);
            if (!file.exists() || file.isDirectory()) {
                file.createNewFile();
            }
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(path + File.separator + fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
            if (iRequester != null) {
                iRequester.onSuccess(ErrCode.Err_No);
            }
        } catch (IOException e) {
            if (iRequester != null) {
                iRequester.onFailure(ErrCode.Err_Exception, e.getMessage());
            }
        }
    }


    /**
     * 删除文件
     *
     * @param path
     * @param fileName
     * @param iRequester
     */
    public void deleteFile(String path, String fileName, IRequester iRequester) {
        File file = new File(path, fileName);
        if (!file.exists() || file.isDirectory()) {
            if (iRequester != null) {
                iRequester.onFailure(ErrCode.Err_Exception, "File Not Found!");
            }
            return;
        }
        file.delete();
        if (iRequester != null) {
            iRequester.onSuccess(ErrCode.Err_No);
        }

    }

}
