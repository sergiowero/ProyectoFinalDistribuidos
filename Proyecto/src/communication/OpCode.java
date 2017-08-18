/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

/**
 *
 * @author Sergio
 */
public enum OpCode {
    NONE(-1),
    SUBSCRIBE(0),
    UNSUBSCRIBE(1),
    NOTIFY(2),
    PUBLISH(3),
    JOIN_GROUP(4),
    LEAVE_GROUP(5);

    private final int code;

    OpCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

    public static OpCode fromCode(int code) {
        for (OpCode type : OpCode.values()) {
            if (type.code() == code) {
                return type;
            }
        }
        return NONE;
    }
}
