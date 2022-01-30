import java.util.ArrayList;
import java.util.List;

public class Policy {
    String policy_number;
    /**
     * policy status:
     * PENDING -- in creation
     * REGISTERED -- created
     * APPROVED -- price has been calculated
     */
    public String policy_status;
    List<Object> policy_objects;
    private static int policyCounter = 0;

    /**
     * Policy constructor :
     * generates a new policy number, displays if constructor worked
     * successfully and sets status to REGISTERED if successful.
     */
    public Policy() {
        this.policy_status = "PENDING";
        generatePolicyNumber();
        this.policy_objects = new ArrayList<Object>();
        if (policy_number != null && policy_status != null) {
            System.out.println("Policy " + this.policy_number + " created!\n");
            this.policy_status = "REGISTERED";
        }
        else
            System.out.println("Could not create a new policy!\n");
    }

    private void generatePolicyNumber() {
        String generated_number = String.format("PG-2022-%06d",policyCounter+1);
        policyCounter++;
        this.policy_number = generated_number;
    }

    public static void displayPolicy(Policy p) {
        System.out.println("\nPolicy number: "+p.policy_number);
        System.out.println("Policy status: "+p.policy_status);
        if (p.policy_objects.isEmpty())
            System.out.println("No objects in this policy!");
        else {
            System.out.println("Policy objects: ");
            p.policy_objects.forEach((o) -> Object.displayObjects(o));
        }
    }

}
