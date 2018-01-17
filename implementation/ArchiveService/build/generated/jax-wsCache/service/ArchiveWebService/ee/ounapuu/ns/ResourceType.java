
package ee.ounapuu.ns;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResourceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ResourceType"&gt;
 *   &lt;restriction base="{http://ns.ounapuu.ee}NonEmptyString"&gt;
 *     &lt;enumeration value="webpage"/&gt;
 *     &lt;enumeration value="audio"/&gt;
 *     &lt;enumeration value="video"/&gt;
 *     &lt;enumeration value="software"/&gt;
 *     &lt;enumeration value="misc"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ResourceType")
@XmlEnum
public enum ResourceType {

    @XmlEnumValue("webpage")
    WEBPAGE("webpage"),
    @XmlEnumValue("audio")
    AUDIO("audio"),
    @XmlEnumValue("video")
    VIDEO("video"),
    @XmlEnumValue("software")
    SOFTWARE("software"),
    @XmlEnumValue("misc")
    MISC("misc");
    private final String value;

    ResourceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ResourceType fromValue(String v) {
        for (ResourceType c: ResourceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
