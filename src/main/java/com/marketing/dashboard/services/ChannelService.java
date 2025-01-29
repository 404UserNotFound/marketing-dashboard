package com.marketing.dashboard.services;

import com.marketing.dashboard.entities.Channel;
import com.marketing.dashboard.repositories.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    public Channel getChannelById(Long id) {
        return channelRepository.findById(id).orElse(null);
    }

    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public Channel updateChannel(Long id, Channel channel) {
        Channel existingChannel = channelRepository.findById(id).orElse(null);
        if (existingChannel != null) {
            existingChannel.setName(channel.getName());
            return channelRepository.save(existingChannel);
        } else {
            return null;
        }
    }

    public void deleteChannel(Long id) {
        channelRepository.deleteById(id);
    }
}
