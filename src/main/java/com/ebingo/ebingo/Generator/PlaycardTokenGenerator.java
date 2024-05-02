package com.ebingo.ebingo.Generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PlaycardTokenGenerator implements IdentifierGenerator {

    @Override
    public String generate(SharedSessionContractImplementor session, Object object) {
        return PKGenerator.generate("playcard");
    }
    
}
