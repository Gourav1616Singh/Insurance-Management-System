package dao;

import entity.Policy;
import myexceptions.PolicyNotFoundException;

import java.util.Collection;

public interface IPolicyService {
    boolean createPolicy(Policy policy);
    Policy getPolicy(int policyId) throws PolicyNotFoundException;
    Collection<Policy> getAllPolicies();
    boolean updatePolicy(Policy policy);
    boolean deletePolicy(int policyId);
}
