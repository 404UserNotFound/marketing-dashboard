package com.marketing.dashboard.controllers;

import com.marketing.dashboard.entities.Channel;
import com.marketing.dashboard.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @GetMapping("")
    public List<Channel> getAllChannels() {
        return channelService.getAllChannels();
    }

    @GetMapping("/{id}")
    public Channel getChannelById(@PathVariable Long id) {
        return channelService.getChannelById(id);
    }

    @PostMapping("")
    public Channel createChannel(@RequestBody Channel channel) {
        return channelService.createChannel(channel);
    }

    @PutMapping("/{id}")
    public Channel updateChannel(@PathVariable Long id, @RequestBody Channel channel) {
        return channelService.updateChannel(id, channel);
    }

    @DeleteMapping("/{id}")
    public void deleteChannel(@PathVariable Long id) {
        channelService.deleteChannel(id);
    }
}
