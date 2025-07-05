package com.careerflow.companyms.company.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.careerflow.companyms.company.CompanyService;
import com.careerflow.companyms.company.dto.ReviewMessage;

@Service
public class ReviewMessageConsumer {

    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage) {
        // Process the review message and update the company rating
        companyService.updateCompanyRating(reviewMessage);

    }
}
