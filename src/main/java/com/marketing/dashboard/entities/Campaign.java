package com.marketing.dashboard.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "campaign")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campaignId;
    private String campaignName;
    private String campaignDescription;
    private String campaignStatus;
    private Double campaignBudget;
    private String campaignStartDate;
    private String campaignEndDate;

    @OneToMany(mappedBy = "campaign")
    private List<CampaignChannel> campaignChannels;

    public Campaign() {

    }

    public Campaign(
            String campaignName,
            String campaignDescription,
            String campaignStatus,
            Double campaignBudget,
            String campaignStartDate,
            String campaignEndDate
    ) {
        this.campaignName = campaignName;
        this.campaignDescription = campaignDescription;
        this.campaignStatus = campaignStatus;
        this.campaignBudget = campaignBudget;
        this.campaignStartDate = campaignStartDate;
        this.campaignEndDate = campaignEndDate;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignDescription() {
        return campaignDescription;
    }

    public void setCampaignDescription(String campaignDescription) {
        this.campaignDescription = campaignDescription;
    }

    public String getCampaignStatus() {
        return campaignStatus;
    }

    public void setCampaignStatus(String campaignStatus) {
        this.campaignStatus = campaignStatus;
    }

    public Double getCampaignBudget() {
        return campaignBudget;
    }

    public void setCampaignBudget(Double campaignBudget) {
        this.campaignBudget = campaignBudget;
    }

    public String getCampaignStartDate() {
        return campaignStartDate;
    }

    public void setCampaignStartDate(String campaignStartDate) {
        this.campaignStartDate = campaignStartDate;
    }

    public String getCampaignEndDate() {
        return campaignEndDate;
    }

    public void setCampaignEndDate(String campaignEndDate) {
        this.campaignEndDate = campaignEndDate;
    }
}
