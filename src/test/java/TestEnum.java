import net.actionpay.util.*;
import net.actionpay.util.Enum;

/**
 * @author Artur Khakimov <djion@ya.ru>
 */
public class TestEnum extends Enum<TestEnum> {

    public static final int ACTIVE = 1;
    public static final int BLOCKED = 2;
    public static final int FROZZEN = 3;

    static {
        register(TestEnum.class, ACTIVE, "active");
        register(TestEnum.class, BLOCKED, "blocked");
        register(TestEnum.class, FROZZEN, "frozzen");
    }

    public static TestEnum active() {
        return Enum.by(TestEnum.class, TestEnum.ACTIVE);
    }

    public static TestEnum blocked() {
        return Enum.by(TestEnum.class, TestEnum.BLOCKED);
    }

    public static TestEnum frozzen() {
        return Enum.by(TestEnum.class, TestEnum.FROZZEN);
    }

    public static void main(String[] args) throws UnexpectedValueException {
        System.out.println(TestEnum.active().getId() < TestEnum.blocked().getId() ?"true":"false");
        System.out.println(TestEnum.frozzen().getId() < TestEnum.blocked().getId() ?"true":"false");
        System.out.println(TestEnum.frozzen().getName());
        System.out.println(TestEnum.frozzen().toMap());
    }

}
