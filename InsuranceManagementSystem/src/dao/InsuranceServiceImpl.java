package dao;

import entity.Policy;
import myexceptions.PolicyNotFoundException;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InsuranceServiceImpl implements IPolicyService {

    @Override
    public boolean createPolicy(Policy policy) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO policy (policyId, policyName) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, policy.getPolicyId());
                preparedStatement.setString(2, policy.getPolicyName());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
           
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Policy getPolicy(int policyId) throws PolicyNotFoundException {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM policy WHERE policyId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, policyId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapResultSetToPolicy(resultSet);
                    } else {
                        throw new PolicyNotFoundException("Policy not found with ID: " + policyId);
                    }
                }
            }
        } catch (SQLException e) {
         
            e.printStackTrace();
            
            throw new RuntimeException("An error occurred while retrieving policy from the database.", e);
        }
    }


    @Override
    public Collection<Policy> getAllPolicies() {
        List<Policy> policies = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM policy";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    Policy policy = mapResultSetToPolicy(resultSet);
                    policies.add(policy);
                }
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "UPDATE policy SET policyName = ? WHERE policyId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, policy.getPolicyName());
                preparedStatement.setInt(2, policy.getPolicyId());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
          
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePolicy(int policyId) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "DELETE FROM policy WHERE policyId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, policyId);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
           
            e.printStackTrace();
            return false;
        }
    }

    private Policy mapResultSetToPolicy(ResultSet resultSet) throws SQLException {
        Policy policy = new Policy();
        policy.setPolicyId(resultSet.getInt("policyId"));
        policy.setPolicyName(resultSet.getString("policyName"));
       
        return policy;
    }
}
