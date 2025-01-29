package com.marketing.dashboard.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "channel")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long channelId;
    private String name;

    @OneToMany(mappedBy = "channel")
    private List<CampaignChannel> campaignChannels;

    public Channel() {

    }

    public Channel(String name) {
        this.name = name;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
