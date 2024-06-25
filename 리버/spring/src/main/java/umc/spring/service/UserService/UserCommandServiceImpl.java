package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodHandler;
import umc.spring.apiPayload.exception.handler.UserMissionHandler;
import umc.spring.converter.UserConverter;
import umc.spring.converter.UserFoodConverter;
import umc.spring.domain.Food;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserFood;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.FoodRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.UserMissionRepository;
import umc.spring.repository.UserRepository;
import umc.spring.web.dto.UserRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    private final FoodRepository foodRepository;

    private final UserMissionRepository userMissionRepository;

    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public User joinUser(UserRequestDTO.UserJoinDTO request) {

        User newUser = UserConverter.toUser(request);
        List<Food> foodList = request.getUserFood().stream()
                .map(category -> {
                    return foodRepository.findById(category).orElseThrow(() -> new FoodHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<UserFood> userFoodList = UserFoodConverter.toUserFoodList(foodList);

        userFoodList.forEach(userFood -> {userFood.setUser(newUser);});

        return userRepository.save(newUser);
    }

    @Override
    @Transactional
    public UserMission challengeUserMission(Long userId, Long missionId, UserRequestDTO.UserMissionChallengeDTO request) {

        Mission mission = missionRepository.findById(missionId).get();
        User user = userRepository.findById(userId).get();
        UserMission userMission = userMissionRepository.findByMissionAndUser(mission, user);

        if (userMission != null) {
            //String missionStatus = userMission.getStatus().toString();
            if (userMission.getStatus()== MissionStatus.ONGOING) {
                throw new UserMissionHandler(ErrorStatus.MISSION_ONGOING);
            }
        }

        UserMission newUserMission = UserConverter.toUserMission(request);

        newUserMission.setUser(userRepository.findById(userId).get());
        newUserMission.setMission(missionRepository.findById(missionId).get());

        return userMissionRepository.save(newUserMission);
    }
}
