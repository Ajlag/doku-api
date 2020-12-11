/*
 * Copyright (c) Institute for Development Impact (I4DI)
 *
 * The software and accompanying documentation that is available for download from Site is the copyrighted and/or
 * patented work of I4DI and/or its suppliers. Use of the software is governed by the terms of the license agreement
 * that is included with such downloaded software. You will not be able to install any software that is accompanied
 * by or includes a license agreement unless you agree to the terms of the included license agreement. If you do not
 * agree to the terms of the included license agreement, you will not be able to install the software.
 *
 * If no license agreement accompanies the software, use of the software will be governed by the Terms of Use. You
 * agree that you will not decompile, reverse engineer, or otherwise attempt to discover the source code of the software
 * available on Site.
 */

package org.i4di.common.dto;

import org.i4di.common.domain.Timestamped;

import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract DTO definition for the {@link Timestamped}.
 *
 * @author ksokolovic
 */
public abstract class TimestampedDTO implements Serializable {

    private Long id;
    private String createdAt;
    private String updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimestampedDTO that = (TimestampedDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
