/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leanstack.ResellerClub.responses;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prolific
 */

@XmlRootElement(name="hashtable")
public class DomainOrderResponse
{
   @XmlElement(name="entry")
   public List<Entry> entries;
}




/*@XmlRootElement(name="string")
class string
{
   
}*/