package com.marketing.dashboard.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "campaign_channel")
public class CampaignChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campaignChannelId;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;


    public Long getCampaignChannelId() {
        return campaignChannelId;
    }

    public void setCampaignChannelId(Long id) {
        this.campaignChannelId = id;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Campaign getCampaign() {
        return campaign;
    }
    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

}
