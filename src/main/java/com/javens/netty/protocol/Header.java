/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.javens.netty.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author liujing01
 * @version Header.java, v 0.1 2018-09-24 21:04 
 */
public class Header {
    private int crcCode = 0xabef0101;
    private int length;//消息长度
    private long sessionID;//会话ID
    private byte type;//消息类型
    private byte priority;//消息优先级
    private Map<String,Object> attachment = new HashMap<>();

    /**
     * Getter method for property <tt>crcCode</tt>.
     *
     * @return property value of crcCode
     */
    public final  int getCrcCode() {
        return crcCode;
    }

    /**
     * Setter method for property <tt>crcCode</tt>.
     *
     * @param crcCode value to be assigned to property crcCode
     */
    public final  void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    /**
     * Getter method for property <tt>length</tt>.
     *
     * @return property value of length
     */
    public final int getLength() {
        return length;
    }

    /**
     * Setter method for property <tt>length</tt>.
     *
     * @param length value to be assigned to property length
     */
    public final void setLength(int length) {
        this.length = length;
    }

    /**
     * Getter method for property <tt>sessionID</tt>.
     *
     * @return property value of sessionID
     */
    public final long getSessionID() {
        return sessionID;
    }

    /**
     * Setter method for property <tt>sessionID</tt>.
     *
     * @param sessionID value to be assigned to property sessionID
     */
    public final void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public final byte getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type value to be assigned to property type
     */
    public final void setType(byte type) {
        this.type = type;
    }

    /**
     * Getter method for property <tt>priority</tt>.
     *
     * @return property value of priority
     */
    public final byte getPriority() {
        return priority;
    }

    /**
     * Setter method for property <tt>priority</tt>.
     *
     * @param priority value to be assigned to property priority
     */
    public final void setPriority(byte priority) {
        this.priority = priority;
    }

    /**
     * Getter method for property <tt>attachment</tt>.
     *
     * @return property value of attachment
     */
    public final Map<String, Object> getAttachment() {
        return attachment;
    }

    /**
     * Setter method for property <tt>attachment</tt>.
     *
     * @param attachment value to be assigned to property attachment
     */
    public final void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Header [crcCode="+crcCode+",length="+length+",sessionID="+sessionID+",type="+type+",priority="+priority
                +",attachment="+attachment+"]";
    }
}
