import java.util.ArrayList;
import java.util.List;

public class Object {
    String object_name;
    List<Subobject> object_subobjects;
    Policy policy = null;

    public Object () {
        throw new RuntimeException("Object needs a name!");
    }

    public Object (String name) {
        this.object_name = name;
        this.object_subobjects = new ArrayList<Subobject>();
    }

    public static void addObjectToPolicy(Policy p, Object o) {
        if (o.policy == null) {
            o.policy = p;
            p.policy_objects.add(o);
            System.out.println("Object " + o.object_name + " added to policy " + p.policy_number);
            System.out.println("\nPolicy information:");
            Policy.displayPolicy(p);
            }
        else {
            System.out.println("Object is already assigned to a policy!");
        }
    }

    public static void displayObjects(Object object) {
        System.out.println("----Object name: "+ object.object_name);
        if (object.object_subobjects.size() > 0) {
            System.out.println("----Object subobjects >");
            object.object_subobjects.forEach((s) -> Subobject.displaySubobject(s));
        }
    }
}
