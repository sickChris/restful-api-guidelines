package de.zalando.zally.rules;

import de.zalando.zally.Violation;
import de.zalando.zally.ViolationType;
import de.zalando.zally.utils.PatternUtil;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HyphenateHttpHeadersRule extends HttpHeadersRule {

    public static final String RULE_NAME = "Use Hyphenated HTTP Headers";
    public static final String RULE_URL = "http://zalando.github.io/restful-api-guidelines/naming/Naming.html" +
            "#must-use-hyphenated-http-headers";
    public static final String DESC_PATTERN = "Header name '%s' is not hyphenated";

    @Override
    public Violation createViolation(String header, Optional<String> path) {
        if (path.isPresent()) {
            return new Violation(RULE_NAME, String.format(DESC_PATTERN, header), ViolationType.MUST, RULE_URL, path.get());
        }
        return new Violation(RULE_NAME, String.format(DESC_PATTERN, header), ViolationType.MUST, RULE_URL);
    }

    @Override
    public boolean isViolation(String header) {
        return !PatternUtil.isHyphenated(header);
    }
}
