package Main;

import java.util.Random;

public class MyTestingClass {
    private String key1;
    private String key2;

    public MyTestingClass(String key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        MyTestingClass other = (MyTestingClass) obj;
        return key1.equals(other.key1) && key2.equals(other.key2);
    }

    @Override
    public String toString() {
        return "MyTestingClass{" +
                "key1='" + key1 + '\'' +
                ", key2='" + key2 + '\'' +
                '}';
    }
}
