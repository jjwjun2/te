package bitcamp.api.common.utils;

import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    static Function<String, Optional<String>> stringNullCheck = x -> Optional.ofNullable(x);


}
