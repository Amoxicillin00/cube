package com.cube.cloud.core.file.storage.clients.minio;

import io.minio.messages.Part;
import lombok.Getter;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-10 12:09
 */
@Getter
public class PartUploadTag implements Serializable {

    private static final long serialVersionUID = 2481451981043999173L;

    private final int partNumber;

    private final String etag;

    public PartUploadTag(int partNumber, String etag) {
        this.partNumber = partNumber;
        this.etag = etag;
    }

    /**
     * 创建 Part
     * @return Part
     */
    public Part createPart() {
        return new Part(this.getPartNumber(), this.etag);
    }
}
