package io.github.brokenearthdev.goodreadsjapi.adapters;

import io.github.brokenearthdev.goodreadsjapi.entities.user.Author;
import io.github.brokenearthdev.goodreadsjapi.internal.impl.AuthorImpl;
import io.github.brokenearthdev.goodreadsjapi.response.ResponseSection;

import java.net.URL;

public class AuthorEntityAdapter extends EntityAdapter<Author> {


    @Override
    public Author convert(ResponseSection section) throws Exception {

        int id = Integer.parseInt(section.getElement("id", 0).text());
        int followerCount = Integer.parseInt(section.getElement("author_followers_count", 0).text());
        int worksCount = Integer.parseInt(section.getElement("works_count", 0).text());

        String name = section.getElement("name", 0).text();
        String gender = section.getElement("gender", 0).text();
        String hometown = section.getElement("hometown", 0).text();
        String bornAt = section.getElement("born_at", 0).text();
        String diedAt = section.getElement("died_at", 0).text();

        URL imgURL = new URL(section.getElement("image_url", 0).text());
        URL link = new URL(section.getElement("link", 0).text());

        return new AuthorImpl(id, followerCount, worksCount, name, hometown, bornAt, diedAt, gender, imgURL,
                link, section);
    }
}
