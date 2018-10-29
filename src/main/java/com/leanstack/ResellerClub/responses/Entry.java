/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leanstack.ResellerClub.responses;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author prolific
 */
public class Entry
{
   @XmlElement(name="string")
   public List<String> values;
}