package com.hp.jipp.model;

import com.google.auto.value.AutoValue;
import com.hp.jipp.encoding.AttributeType;
import com.hp.jipp.encoding.Keyword;
import com.hp.jipp.encoding.KeywordType;

@AutoValue
public abstract class IdentifyAction extends Keyword {
    public static final IdentifyAction Display = of("display");
    public static final IdentifyAction Flash = of("flash");
    public static final IdentifyAction Sound = of("sound");
    public static final IdentifyAction Speak = of("speak");

    public static final KeywordType.Encoder<IdentifyAction> ENCODER = KeywordType.encoderOf(
            IdentifyAction.class, new Keyword.Factory<IdentifyAction>() {
                @Override
                public IdentifyAction of(String name) {
                    return new AutoValue_IdentifyAction(name);
                }
            });

    public static AttributeType<IdentifyAction> typeOf(String name) {
        return new KeywordType<>(ENCODER, name);
    }

    public static IdentifyAction of(String name) {
        return ENCODER.getFactory().of(name);
    }
}
