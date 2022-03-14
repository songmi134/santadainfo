package com.mountain.mountain.controller.mountain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;


import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.NONE)
@Component
public class PBMTListDTO implements serializable {

    private List<PBMTListDTO> item;

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    public List<PBMTListDTO> getItem(){
        return item;
    }

    public void setItem(List<PBMTListDTO> item) {
        this.item = item;
    }

}
