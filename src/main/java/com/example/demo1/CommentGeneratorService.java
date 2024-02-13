package com.example.demo1;

import java.net.URL;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * A service class responsible for generating comments using the OpenAI API.
 */
public class CommentGeneratorService {

    // OpenAI API key
    private static final String API_KEY = "<Enter API KEY of Open API>";
    // OpenAI GPT-3 endpoint
    private static final String GPT_ENDPOINT = "https://api.openai.com/v1/completions";

    /**
     * Generates a comment for the given code using the OpenAI API.
     *
     * @param SelectedCode The code for which the comment is to be generated.
     * @return The generated comment.
     * @throws Exception If an error occurs during the API request.
     */
    public String generateComment(String selectedCode) throws Exception {
        String requestBody = "{\"model\":\"text-davinci-003\",\"prompt\":\"" + selectedCode + "\",\"max_tokens\":50}";
        URL url = new URL(GPT_ENDPOINT);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization",  "Bearer "+API_KEY);
        con.setDoOutput(true);

        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(requestBody);
            wr.flush();
        }
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return extractCommentFromResponse(response.toString());
    }

    /**
     * Extracts the comment from the API response.
     *
     * @param response The response received from the API.
     * @return The extracted comment.
     */
    private String extractCommentFromResponse(String comment) {
        return "Generated Comment: " + comment;
    }
    }
