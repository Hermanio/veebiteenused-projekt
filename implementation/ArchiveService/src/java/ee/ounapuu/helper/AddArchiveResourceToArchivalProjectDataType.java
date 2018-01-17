/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ounapuu.helper;

import java.math.BigInteger;

/**
 * Helper class to accept special JSON as input for a method.
 */
public class AddArchiveResourceToArchivalProjectDataType {

    private BigInteger resourceId;

    public AddArchiveResourceToArchivalProjectDataType() {

    }

    public AddArchiveResourceToArchivalProjectDataType(BigInteger resourceId) {
        this.resourceId = resourceId;
    }

    public BigInteger getResourceId() {
        return resourceId;
    }

    public void setResourceId(BigInteger resourceId) {
        this.resourceId = resourceId;
    }

}
