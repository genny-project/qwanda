package life.genny.qwanda.converter;

import java.math.BigDecimal;

import javax.money.MonetaryAmount;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.javamoney.moneta.Money;

@Converter
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(MonetaryAmount attribute) {
    	return new BigDecimal(attribute.getNumber().doubleValue());
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(BigDecimal dbData) {
    	return Money.of(dbData.doubleValue(), "AUD");
    }
}