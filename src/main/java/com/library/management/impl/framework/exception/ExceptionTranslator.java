package com.library.management.impl.framework.exception;


/**
 * This class transforms SQLException into a Spring specific DataAccessException. The idea behind this is borrowed from
 * Adam Zell's Gist
 *
 * @author Petri Kainulainen
 * @author Adam Zell
 * @author Lukas Eder
 * @see <a
 * href="http://www.petrikainulainen.net/programming/jooq/using-jooq-with-spring-configuration/">http://www.petrikainulainen.net/programming/jooq/using-jooq-with-spring-configuration/</a>
 * @see <a href="https://gist.github.com/azell/5655888">https://gist.github.com/azell/5655888</a>
 */
public class ExceptionTranslator{

    /**
     * Generated UID
     */
    private static final long serialVersionUID = -2450323227461061152L;

    public void exception(Object ctx) {
    }
}