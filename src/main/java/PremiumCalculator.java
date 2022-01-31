import java.util.ArrayList;
import java.util.List;

public class PremiumCalculator {

    /**
     * coefficients for risk types.
     *
     * to add additional risk types:
     * 1. define the risk coefficient as static float
     *     e.g. coefficient_*risk name* = *value*;
     * 2. in current class calculate(Policy policy) method
     *     add a float variable for storing the sum
     *     of subobjects insured with the new risk
     *     e.g. sum_insured_*risk name*
     * 3. in tha same method, in switch() statement
     *     add a case for new risk the same way as for previous risks
     *     e.g. case "*risk name*" -> ...
     * 4. add mathematical formula and special conditions
     *     for new risk type after switch() and for loop,
     *     and sum it to total policy premium
     */
    static float coefficient_fire = 0.014F;
    static float coefficient_theft = 0.11F;

    /**
     * method for calculating the premium.
     * To calculate premium, there has to be a policy
     * with an object, that has at least one subobject.
     * Object needs to be created
     *  and added to the policy,
     * Subobject needs to be created
     *  and added to an object.
     */
    public static void calculate(Policy policy) {
        if (!policy.policy_objects.isEmpty()) {
            List<Object> objects;
            objects = policy.policy_objects;
            /**
             * collects all subobjects from the policy
             */
            List<Subobject> subobjects = new ArrayList<Subobject>();
            for (Object object: objects) {
                int size = object.object_subobjects.size();
                for (int i = 0; i < size; i++) {
                    subobjects.add(object.object_subobjects.get(i));
                }
            }
            int l = subobjects.size();
            System.out.println(l+" subobjects found");
            if (l > 0) {
                /**
                 * defined variables for summing the
                 * subobject insured sums, based on risk type
                 */
                float sum_insured_fire = 0;
                float sum_insured_theft = 0;

                for (Subobject s: subobjects) {

                    switch (s.risk_type) {
                        case "FIRE" -> sum_insured_fire += s.insured_price;

                        case "THEFT" -> sum_insured_theft += s.insured_price;

                        default -> System.out.println("Unknown risk type for subobject "
                                + s.subobject_name + ": " + s.risk_type);

                    }
                }
                /**
                 * formulas for calculating policy premium
                 */
                if (sum_insured_fire > 100F) coefficient_fire = 0.024F;
                if (sum_insured_theft >= 15F) coefficient_theft = 0.05F;

                float premium = sum_insured_fire * coefficient_fire +
                                sum_insured_theft * coefficient_theft;
                /**
                 * displaying the policy premium
                 */
                String str_premium = String.format(" calculated premium is %.2f EUR", premium);
                System.out.println("Policies " + policy.policy_number + str_premium);
                policy.policy_status = "APPROVED";
            } else {
                throw new RuntimeException("Objects need to have " +
                        "at least one subobject to calculate premium!");
            }
        } else {
            throw new RuntimeException("Policy needs to have an object!");
        }
    }

    /**
     * main method
     * used for creating the policies, objects and subobjects,
     * and running the policy premium calculator
     * @param args
     */
    public static void main(String[] args) {
        Policy p1 = new Policy();
        Object o1 = new Object("Condo");
        Object.addObjectToPolicy(p1, o1);

        Subobject s1 = new Subobject("Monitor",100.00F,"FIRE");
        Subobject.addSubobjectToObject(o1, s1);
        Subobject s2 = new Subobject("Phone charger", 8.00F, "THEFT");
        Subobject.addSubobjectToObject(o1, s2);

        Policy p2 = new Policy();
        Object o2 = new Object("Private garage");
        Object.addObjectToPolicy(p2, o2);

        Subobject s3 = new Subobject("Fridge",500.00F, "FIRE");
        Subobject.addSubobjectToObject(o2, s3);
        Subobject s4 = new Subobject("Power drill", 102.51F, "THEFT");
        Subobject.addSubobjectToObject(o2, s4);

        Policy.displayPolicy(p1);
        calculate(p1);
        calculate(p2);
    }
}
