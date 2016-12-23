package com.hp.jipp.encoding;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class AttributeTest {

    @Test
    public void octetString() throws IOException {
        Attribute<byte[]> attribute = OctetAttribute.create(Tag.OctetString, "name", "value".getBytes());
        assertArrayEquals(new byte[] {
                (byte)0x30, // OctetString
                (byte)0x00,
                (byte)0x04,
                'n', 'a', 'm', 'e',
                (byte)0x00,
                (byte)0x05,
                'v', 'a', 'l', 'u', 'e'
        }, toBytes(attribute));
        attribute = cycle(attribute);
        assertEquals(Tag.OctetString, attribute.getValueTag());
        assertEquals("name", attribute.getName());
        assertArrayEquals("value".getBytes(), attribute.getValue(0));
    }

    @Test
    public void multiOctetString() throws IOException {
        Attribute<byte[]> attribute = OctetAttribute.create(Tag.NameWithoutLanguage, "name",
                "value".getBytes(),
                "value2".getBytes());
        assertArrayEquals("value".getBytes(), attribute.getValue(0));
        assertArrayEquals("value2".getBytes(), attribute.getValue(1));
    }


    @Test
    public void multiBoolean() throws IOException {
        Attribute<Boolean> attribute = cycle(BooleanAttribute.create(Tag.BooleanValue, "name",
                true, false));
        assertEquals(ImmutableList.of(true, false), attribute.getValues());
    }

    @Test
    public void multiInteger() throws IOException {
        Attribute<Integer> attribute = cycle(IntegerAttribute.create(Tag.IntegerValue, "name",
                -50505, 50505));
        assertEquals(ImmutableList.of(-50505, 50505), attribute.getValues());
    }

    @Test
    public void collection() throws IOException {
        // Let's encode:
        // media-col = {
        //   media-color: blue,
        //   media-size: [ {
        //       x-dimension = 6,
        //       y-dimension = 4
        //     }, {
        //       x-dimension = 12,
        //       y-dimension = 5
        //     }
        //  }

        // Hideously ugly but accurate attribute construction
        Attribute<Map<String, Attribute<?>>> mediaCol = CollectionAttribute.create("media-col",
                ImmutableMap.<String, Attribute<?>>builder()
                        .put("media-color", StringAttribute.create(Tag.Keyword, "", "blue"))
                        .put("media-size", CollectionAttribute.create(
                                ImmutableMap.<String, Attribute<?>>builder()
                                        .put("x-dimension", IntegerAttribute.create(Tag.IntegerValue, "", 6))
                                        .put("y-dimension", IntegerAttribute.create(Tag.IntegerValue, "", 4))
                                        .build(),
                                ImmutableMap.<String, Attribute<?>>builder()
                                        .put("x-dimension", IntegerAttribute.create(Tag.IntegerValue, "", 12))
                                        .put("y-dimension", IntegerAttribute.create(Tag.IntegerValue, "", 5))
                                        .build()))
                        .build());

        mediaCol = cycle(mediaCol);

        assertEquals("media-col", mediaCol.getName());
        assertEquals("blue", mediaCol.getValues().get(0).get("media-color").getValues().get(0));
        assertEquals(6, mediaCol.getValue(0).get("media-size").asCollection()
                .getValue(0).get("x-dimension").getValue(0));
    }

//    @Test
//    public void simpleCollection() throws IOException {
//        Map<String, ?> map = ImmutableMap.of(
//                "media-color", TagValue(Tag.Keyword, "blue"),
//                "media-size", ImmutableList.of(
//                        ImmutableMap.of(
//                                "x-dimension", 6,
//                                "y-dimension", 4),
//                        ImmutableMap.of(
//                                "x-dimension", 12,
//                                "y-dimension", 5)));
//
//    }

    @SuppressWarnings("unchecked")
    private <T> Attribute<T> cycle(Attribute<T> attribute) throws IOException {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(toBytes(attribute)));
        return (Attribute<T>) AttributeGroup.readAttribute(in, Tag.read(in));
    }

    private byte[] toBytes(Attribute attribute) throws IOException {
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        attribute.write(new DataOutputStream(bytesOut));
        return bytesOut.toByteArray();
    }
}