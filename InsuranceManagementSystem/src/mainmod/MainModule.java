package mainmod;

import dao.InsuranceServiceImpl;
import entity.Policy;

import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        InsuranceServiceImpl insuranceService = new InsuranceServiceImpl();
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Insert Policy");
            System.out.println("2. Retrieve A Policy");
            System.out.println("3. Update Policy");
            System.out.println("4. Delete Policy");
            System.out.println("5. View All Policies");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    
                    System.out.println("Enter Policy ID:");
                    int policyId = scanner.nextInt();
                    System.out.println("Enter Policy Name:");
                    String policyName = scanner.next();

                    Policy policyToInsert = new Policy(policyId, policyName);
                    boolean insertSuccess = insuranceService.createPolicy(policyToInsert);

                    if (insertSuccess) {
                        System.out.println("Policy inserted successfully.");
                    } else {
                        System.out.println("Failed to insert policy.");
                    }
                    break;

                case 2:
                   
                    System.out.println("Enter Policy ID to retrieve:");
                    int retrievePolicyId = scanner.nextInt();

                    try {
                        Policy retrievedPolicy = insuranceService.getPolicy(retrievePolicyId);
                        System.out.println("Retrieved Policy: " + retrievedPolicy);
                    } catch (Exception e) {
                        System.out.println("Policy not found or an error occurred: " + e.getMessage());
                    }
                    break;

                case 3:
                   
                    System.out.println("Enter Policy ID to update:");
                    int updatePolicyId = scanner.nextInt();
                    System.out.println("Enter new Policy Name:");
                    String newPolicyName = scanner.next();

                    Policy policyToUpdate = new Policy(updatePolicyId, newPolicyName);
                    boolean updateSuccess = insuranceService.updatePolicy(policyToUpdate);

                    if (updateSuccess) {
                        System.out.println("Policy updated successfully.");
                    } else {
                        System.out.println("Failed to update policy.");
                    }
                    break;

                case 4:
                
                    System.out.println("Enter Policy ID to delete:");
                    int deletePolicyId = scanner.nextInt();

                    boolean deleteSuccess = insuranceService.deletePolicy(deletePolicyId);

                    if (deleteSuccess) {
                        System.out.println("Policy deleted successfully.");
                    } else {
                        System.out.println("Failed to delete policy.");
                    }
                    break;

                case 5:
                    
                    System.out.println("All Policies:");
                    insuranceService.getAllPolicies().forEach(System.out::println);
                    break;

                case 6:
                    System.out.println("Exiting the program. THANK YOU");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        } while (choice != 6);

        scanner.close();
    }
}
