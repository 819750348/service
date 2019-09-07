package com.snake.web.boot.module.rup.model;

import com.snake.web.boot.module.system.model.Attachment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "model_category")
public class ModelCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 文件描述文件模板ID
     */
    @Column(name = "file_id")
    private Long fileId;

    /**
     * 是否设置,默认为否.0:标识否,1:标示是
     */
    @Column(name = "set_file")
    private Boolean setFile;


    @Transient
    @Getter
    @Setter
    private Attachment file;
    /**
     * 图标样式
     */
    private String icon;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取文件描述文件模板ID
     *
     * @return file_id - 文件描述文件模板ID
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * 设置文件描述文件模板ID
     *
     * @param fileId 文件描述文件模板ID
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * 获取是否设置,默认为否.0:标识否,1:标示是
     *
     * @return set_file - 是否设置,默认为否.0:标识否,1:标示是
     */
    public Boolean getSetFile() {
        return setFile;
    }

    /**
     * 设置是否设置,默认为否.0:标识否,1:标示是
     *
     * @param setFile 是否设置,默认为否.0:标识否,1:标示是
     */
    public void setSetFile(Boolean setFile) {
        this.setFile = setFile;
    }

    /**
     * 获取图标样式
     *
     * @return icon - 图标样式
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标样式
     *
     * @param icon 图标样式
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", parentId=").append(parentId);
        sb.append(", fileId=").append(fileId);
        sb.append(", setFile=").append(setFile);
        sb.append(", icon=").append(icon);
        sb.append("]");
        return sb.toString();
    }
}