package test.vakoms.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.vakoms.domain.Fare;
import test.vakoms.domain.User;
import test.vakoms.exceptions.ResourceNotFoundException;
import test.vakoms.infrastructure.persistence.FareRepository;
import test.vakoms.infrastructure.persistence.UserRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class FareService {
    @Autowired
    private FareRepository fareRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Fare> getAllFares() {
        List<Fare> fares = new ArrayList<>();
        fareRepository.findAll().forEach(fares::add);
        return fares;
    }

    public Set<Fare> getFaresByUserId(Integer userId) {
        return userRepository.findById(userId).get().getFares();
    }

    public Fare getFare(Integer id) throws ResourceNotFoundException {
        return fareRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fare with this id is not found"));
    }

    @Transactional
    public Set<Fare> findAllFaresByUuid(String uuid) {
        String regexp = "((\\S|^)*" + uuid + "(\\S|$)*|((\\S|^)*" + uuid.toLowerCase(Locale.ENGLISH)
                + "(\\S|$)*)|((\\S|^)*" + uuid.toUpperCase(Locale.ENGLISH) + "(\\S|$)*))";
        List<Fare> fares = getAllFares();
        Set<Fare> foundFares = new HashSet<>();
        for (Fare fare : fares) {
            if (fare.getUuid().matches(regexp)) {
                foundFares.add(fare);
            }
        }

        return foundFares;
    }

    @Transactional
    public void addFare(Fare fare) {
        String regexp = "\\s*|\\W*";
        if (!fare.getUuid().matches(regexp) && !fare.getStatus().matches(regexp)) {
            fareRepository.save(fare);
        }
    }

    @Transactional
    public void assignFareToUser(Integer fareId, Integer userId) {
        Fare fare = fareRepository.findById(fareId).get();
        User user = userRepository.findById(userId).get();

        fare.setUser(user);
        fareRepository.save(fare);
    }

    @Transactional
    public void updateFare(Integer id, Fare fare) {
        Fare fareToUpdate = fareRepository.findById(id).get();

        if (!fare.getUuid().isEmpty()) {
            fareToUpdate.setUuid(fare.getUuid());
        }

        if (!fare.getStatus().isEmpty()) {
            fareToUpdate.setStatus(fare.getStatus());
        }

        if (fare.getUser() != null) {
            fareToUpdate.setUser(fare.getUser());
        }

        fareRepository.save(fareToUpdate);
    }

    @Transactional
    public void updateFareUser(Integer id, Integer userId) {
        User user = userRepository.findById(userId).get();
        Fare fareToUpdate = fareRepository.findById(id).get();

        fareToUpdate.setUser(user);

        fareRepository.save(fareToUpdate);
    }

    @Transactional
    public void deleteFare(Integer id) {
        fareRepository.deleteById(id);
    }

    @Transactional
    public Fare findFareByUuid(String uuid) {
        return fareRepository.findByUuid(uuid);
    }

    @Transactional
    public void deleteFareUser(Integer id) {
        Fare fareToUpdate = fareRepository.findById(id).get();

        fareToUpdate.setUser(null);

        fareRepository.save(fareToUpdate);
    }
}
