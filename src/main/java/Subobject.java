import java.util.Locale;

public class Subobject {
    String subobject_name;
    float insured_price;
    String risk_type; // FIRE or THEFT
    Object assigned_object = null;

    public Subobject(String name, float price, String risk) {
        this.subobject_name = name;
        this.insured_price = price;
        this.risk_type = risk.toUpperCase(Locale.ROOT);
    }

    public static void displaySubobject(Subobject subobject) {
        System.out.println("--------Item: " + subobject.subobject_name);
        System.out.println(String.format("--------Insured price: %.2f EUR", subobject.insured_price));
        System.out.println("--------Risk type: " + subobject.risk_type + "\n");
    }

    public static void addSubobjectToObject(Object obj, Subobject subobj) {
        if (subobj.assigned_object == null) {
            subobj.assigned_object = obj;
            System.out.println("\nCurrent subobjects in object: " + obj.object_subobjects.stream().count());
            obj.object_subobjects.add(subobj);
            System.out.println("Subobject " + subobj.subobject_name + " added to object " + obj.object_name + "!\n");
            Object.displayObjects(obj);
        }
        else {
            System.out.println("Subobject already assigned to an object!");
        }
    }

}
