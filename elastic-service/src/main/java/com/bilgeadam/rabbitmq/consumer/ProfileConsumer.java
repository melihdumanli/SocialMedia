package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.mapper.ProfileMapper;
import com.bilgeadam.rabbitmq.model.ProfileNotification;
import com.bilgeadam.repository.IProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileConsumer {
    private final IProfileRepository repository;
    private final ProfileMapper profileMapper;

    @RabbitListener(queues = "profile-save-queue")
    public void handleProfileSave(ProfileNotification notification) {
        repository.save(profileMapper.toProfile(notification));
        log.info("Profile saved: {}", notification.toString());
    }
}
