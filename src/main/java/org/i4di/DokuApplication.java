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
package org.i4di;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class DokuApplication {

    public static void main(String[] args) {
        SpringApplication.run(DokuApplication.class);
    }

}
