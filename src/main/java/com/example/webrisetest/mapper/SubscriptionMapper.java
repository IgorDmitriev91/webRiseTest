package com.example.webrisetest.mapper;

import com.example.webrisetest.config.MapperConfiguration;
import com.example.webrisetest.dto.Subscription;
import com.example.webrisetest.model.SubscriptionModel;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface SubscriptionMapper {

    Subscription toDTO(SubscriptionModel subscriptionModel);
}
