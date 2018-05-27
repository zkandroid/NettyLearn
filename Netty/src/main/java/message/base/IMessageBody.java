package message.base;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class IMessageBody {
	
	
	public boolean decode(ByteBuf buf) throws IllegalArgumentException, IllegalAccessException
    {
		Field[] fields = this.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; ++i) {
			Field field = fields[i];
			if (field.getType() == Character.class || field.getType() == char.class) {
				field.setAccessible(true);
				field.set(this, decodeChar (buf));
			} else if (field.getType() == Integer.class || field.getType() == int.class) {
				field.setAccessible(true);
				field.set(this, decodeInt32 (buf));
			} else if (field.getType() == String.class) {
				field.setAccessible(true);
				field.set(this, decodeString (buf));
			} else if (field.getType() == ByteData.class) {
				field.setAccessible(true);
				field.set(this, decodeBytes(buf));
			} 
			/*else if (field.FieldType == typeof(Int64)) {
				field.SetValue (this, DecodeInt64 (bytes, ref offset));
			} else if (field.FieldType == typeof(UInt16)) {
				field.SetValue (this, DecodeUInt16 (bytes, ref offset));
			} else if (field.FieldType == typeof(UInt32)) {
				field.SetValue (this, DecodeUInt32 (bytes, ref offset));
			} else if (field.FieldType == typeof(UInt64)) {
				field.SetValue (this, DecodeUInt64 (bytes, ref offset));
			} else if (field.FieldType == typeof(byte)) {
				field.SetValue (this, DecodeByte (bytes, ref offset));
			} else if (field.FieldType == typeof(Int16)) {
				field.SetValue (this, DecodeInt16 (bytes, ref offset));
			} else if (field.FieldType.IsSubclassOf (typeof(Protocal))) {
				((Protocal)field.GetValue (this)).Decode (bytes, ref offset);
			} 
			*/
			else {
				System.out.println("Unsupport Decode Field Type: " + field.getType());
			}
		}
        return true;
    }

	public ByteBuf encode() throws IllegalArgumentException, IllegalAccessException
    {
		ByteBuf buf = Unpooled.buffer(1000);
		Field[] fields = this.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; ++i) {
			Field field = fields[i];
			if (field.getType() == Character.class) {
				field.setAccessible(true);
				encodeChar((Character)field.get(this), buf);
			} else if (field.getType() == char.class) {
				field.setAccessible(true);
				encodeChar(field.getChar(this), buf);
			} else if (field.getType() == int.class) {
				field.setAccessible(true);
				encodeInt32 (field.getInt(this), buf);
			} else if (field.getType() == Integer.class) {
				field.setAccessible(true);
				encodeInt32 ((Integer)field.get(this), buf);
			} else if (field.getType() == String.class) {
				field.setAccessible(true);
				encodeString((String)field.get(this), buf);
			} else if (field.getType() == ByteData.class) {
				field.setAccessible(true);
				encodeByteData((ByteData)field.get(this), buf);
			} 
			/*
			else if (field.FieldType == typeof(UInt16)) {
				EncodeUInt16 ((UInt16)field.GetValue (this), bytes, ref offset);
			} else if (field.FieldType == typeof(UInt32)) {
				EncodeUInt32 ((UInt32)field.GetValue (this), bytes, ref offset);
			} else if (field.FieldType == typeof(Int16)) {
				EncodeInt16 ((Int16)field.GetValue (this), bytes, ref offset);
			} else if (field.FieldType.IsSubclassOf (typeof(Protocal))) {
				((Protocal)field.GetValue (this)).Encode (bytes, ref offset);
			} else if (field.FieldType == typeof(byte)) {
				EncodeByte ((byte)field.GetValue (this), bytes, ref offset);
			} else if (field.FieldType == typeof(UInt64)) {
				EncodeUInt64 ((UInt64)field.GetValue (this), bytes, ref offset);
			} else if (field.FieldType == typeof(Int64)) {
				EncodeInt64 ((Int64)field.GetValue (this), bytes, ref offset);
			}*/
			else {
				System.out.println("Unsupport Encode Field Type: " + field.getType());
			}
		}
        return buf;
    }

    // char
    private boolean encodeChar(char value, ByteBuf buf)
    {
        buf.writeChar(value);
        return true;
    }
    private static char decodeChar(ByteBuf buf)
    {
        char value = buf.readChar();
        return value;
    }

    // int32
    private boolean encodeInt32(int value, ByteBuf buf)
    {
        buf.writeInt(value);
        return true;
    }

    private static int decodeInt32(ByteBuf buf)
    {
        int value = buf.readInt();
        return value;
    }

    // string
    private boolean encodeString(String value, ByteBuf buf)
    {
    	byte[] bytes = value.getBytes(Charset.forName("UTF-8"));
    	
        int strLen = bytes.length;

        if(!encodeInt32(strLen, buf))
        	return false;
        buf.writeBytes(bytes);
        return true;
    }

    private String decodeString(ByteBuf buf)
    {
        int len = buf.readInt();
        String value = new String(buf.readBytes(len).toString(Charset.forName("UTF-8")));
        return value;
    }

	// ByteData
    private boolean encodeByteData(ByteData value, ByteBuf buf)
	{
		encodeInt32 (value.getLength(), buf);
		buf.writeBytes(value.getBuf());
		return true;
	}

    private ByteData decodeBytes(ByteBuf buf)
	{
		int length = buf.readInt();
		ByteData byteData = new ByteData();
		byteData.initBytes(length);
		buf.readBytes(byteData.getBuf());
		return byteData;
	}
	/*
    // byte
    public static boolean encodeByte(byte value, ByteBuf buf)
    {
        buf.writeByte(value);
        return true;
    }
    public static byte decodeByte(ByteBuf buf)
    {
        byte value = buf.readByte();
        return value;
    }
    // int64
    public static bool EncodeInt64(Int64 value, byte[] bytes, ref int offset)
    {
        byte[] valueBytes = BitConverter.GetBytes(IPAddress.HostToNetworkOrder(value));
        Array.Copy(valueBytes, 0, bytes, offset, valueBytes.Length);
        offset += valueBytes.Length;

        return true;
    }
    public static Int64 DecodeInt64(byte[] bytes, ref int offset)
    {
        Int64 value = IPAddress.NetworkToHostOrder(BitConverter.ToInt64(bytes, offset));
        offset += sizeof(Int64);
        ByteBuf buf;
        
        return value;
    }

    // uint16
    public static bool EncodeUInt16(UInt16 value, byte[] bytes, ref int offset)
    {
        byte[] valueBytes = BitConverter.GetBytes(IPAddress.HostToNetworkOrder((Int16)value));
        Array.Copy(valueBytes, 0, bytes, offset, valueBytes.Length);
        offset += valueBytes.Length;

        return true;
    }
    public static UInt16 DecodeUInt16(byte[] bytes, ref int offset)
    {
        UInt16 value = (UInt16)IPAddress.NetworkToHostOrder(BitConverter.ToInt16(bytes, offset));
        offset += sizeof(UInt16);

        return value;
    }

    // uint32
    public static bool EncodeUInt32(UInt32 value, byte[] bytes, ref int offset)
    {
        byte[] valueBytes = BitConverter.GetBytes(IPAddress.HostToNetworkOrder((Int32)value));
        Array.Copy(valueBytes, 0, bytes, offset, valueBytes.Length);
        offset += valueBytes.Length;
        ByteBuf buf;
      
        return true;
    }
    public static UInt32 DecodeUInt32(byte[] bytes, ref int offset)
    {
        UInt32 value = (UInt32)IPAddress.NetworkToHostOrder(BitConverter.ToInt32(bytes, offset));
        offset += sizeof(UInt32);

        return value;
    }

    // uint64
    public static bool EncodeUInt64(UInt64 value, byte[] bytes, ref int offset)
    {
        byte[] valueBytes = BitConverter.GetBytes(IPAddress.HostToNetworkOrder((Int64)value));
        Array.Copy(valueBytes, 0, bytes, offset, valueBytes.Length);
        offset += valueBytes.Length;

        return true;
    }

    public static UInt64 DecodeUInt64(byte[] bytes, ref int offset)
    {
        UInt64 value = (UInt64)IPAddress.NetworkToHostOrder(BitConverter.ToInt64(bytes, offset));
        offset += sizeof(UInt64);

        return value;
    }

   
	 // int16
    public static bool EncodeInt16(Int16 value, byte[] bytes, ref int offset)
    {
        byte[] valueBytes = BitConverter.GetBytes(IPAddress.HostToNetworkOrder(value));
        Array.Copy(valueBytes, 0, bytes, offset, valueBytes.Length);
        offset += valueBytes.Length;

        return true;
    }
    public static Int16 DecodeInt16(byte[] bytes, ref int offset)
    {
        Int16 value = IPAddress.NetworkToHostOrder(BitConverter.ToInt16(bytes, offset));
        offset += sizeof(Int16);

        return value;
    }
    */

}
