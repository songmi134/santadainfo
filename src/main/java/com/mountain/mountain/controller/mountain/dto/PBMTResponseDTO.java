package com.mountain.mountain.controller.mountain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.NONE)
@Component
public class PBMTResponseDTO {

    @XmlAttribute(name = "mntnid")
    private Long mountainNo;

    @XmlElement(name = "prmntrcmmncoursedscrt")
    private String addressDetail;

    @XmlElement(name = "mntninfohght")
    private String mountainHeight;

    @XmlElement(name = "mntninfodtlinfocont")
    private String mountainInfo;

    @XmlElement(name = "hndfmsmtnslctnrson")
    private String mountainSum;

    @XmlElement(name = "mntnattchimageseq")
    private String orgUrl;

    @XmlElement(name = "pbtrninfodscrt")
    private String transInfo;

}
