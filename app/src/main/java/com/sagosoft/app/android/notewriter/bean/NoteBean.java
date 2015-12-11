package com.sagosoft.app.android.notewriter.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by FRED on 2015/12/4.
 */
public class NoteBean extends NoteGroup implements Serializable {

    private int id;
    /**
     * note name
     */
    private String name;
    private String contentUri;
    private String content;

    private String logo;
    private String createTime;
    private String lastModifyTime;

    /**
     * 笔记类型
     */
    private NoteType noteType;
    private SourceType sourceType;
    private List<NoteBean> childs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentUri() {
        return contentUri;
    }

    public void setContentUri(String contentUri) {
        this.contentUri = contentUri;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public com.sagosoft.app.android.notewriter.bean.NoteType getNoteType() {
        return noteType;
    }

    public void setNoteType(com.sagosoft.app.android.notewriter.bean.NoteType noteType) {
        this.noteType = noteType;
    }

    public com.sagosoft.app.android.notewriter.bean.SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(com.sagosoft.app.android.notewriter.bean.SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public List<NoteBean> getChilds() {
        return childs;
    }

    public void setChilds(List<NoteBean> childs) {
        this.childs = childs;
    }
}

enum NoteType {
    Notes(1),
    Novel(1 << 1),
    Secret(1 << 2),
    Blabla(1 << 3),
    Motion(1 << 4),
    Funny(1 << 5);

    int value;

    NoteType(int value) {
        this.value = value;
    }

}
enum SourceType {
    Cloud(1),
    Local(1 << 1),
    Cache(1 << 2),
    Syncing(1 << 3);

    int value;
    SourceType(int value) {
        this.value = value;
    }

}