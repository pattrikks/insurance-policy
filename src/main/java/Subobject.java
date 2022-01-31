import java.util.Locale;

public class Subobject {
    String subobject_name;
    float insured_price;
    String risk_type; // FIRE or THEFT
    Object assigned_object = null;

    public Subobject() { this("",0F,""); }

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

    public boolean SubobjectIsComplete(Subobject subobject) {
        if (subobject.subobject_name.isEmpty()) return false;
            else if (subobject.risk_type.isEmpty()) return false;
                else if (subobject.insured_price <= 0F) return false;
                    else if (subobject.assigned_object != null) return false;
                        else return true;
    }

    public static void addSubobjectToObject(Object obj, Subobject subobj) {
        if (subobj.SubobjectIsComplete(subobj)) {
            subobj.assigned_object = obj;
            System.out.println("\nCurrent subobjects in object: " + obj.object_subobjects.stream().count());
            obj.object_subobjects.add(subobj);
            System.out.println("Subobject " + subobj.subobject_name + " added to object " + obj.object_name + "!\n");
            Object.displayObjects(obj);
        }
        else {
            if (subobj.assigned_object != null) System.out.println("Subobject already assigned to an object!");
            else throw new RuntimeException("Subobject not fully defined!");
        }
    }

}
