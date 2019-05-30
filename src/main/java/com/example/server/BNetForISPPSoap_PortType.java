/**
 * BNetForISPPSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.example.server;

public interface BNetForISPPSoap_PortType extends java.rmi.Remote {
    public String subCustInfo(String xmlString) throws java.rmi.RemoteException;
    public String subscription(String xmlString) throws java.rmi.RemoteException;
    public String confirmOrder(String xmlString) throws java.rmi.RemoteException;
    public String unSubCustInfo(String xmlString) throws java.rmi.RemoteException;
}
