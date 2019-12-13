package com.likai.gateway.model.Image;

import com.likai.gateway.model.baseMessage;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class messageImageResponse extends baseMessage {

    @XmlElementWrapper(name = "Image")
    @XmlElement(name = "MediaId", type = String.class)
    private List<String> MediaIdList;

    public List<String> getMediaIdList() {
        return MediaIdList;
    }

    public void setMediaIdList(List<String> mediaIdList) {
        MediaIdList = mediaIdList;
    }

    @Override
    public String toString() {
        return "messageImageResponse{" +
                "MediaIdList=" + MediaIdList +
                '}';
    }
}
