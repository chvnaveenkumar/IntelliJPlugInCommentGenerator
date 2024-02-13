# Comment Generator Plugin
## This package contains a plugin for generating comments for selected code in IntelliJ IDEA.

### Usage
1. Install the plugin in IntelliJ IDEA.
2. Open a Java file in the editor.
3. Select the code you want to generate a comment for.
4. Right-click and choose the "Generate Comment" option from the context menu.
5. A comment will be generated and inserted at the current caret position in the editor.
   
### Features
AI-Based Comment Generation: By default, the plugin uses an AI-based service to generate comments for selected code. The service analyzes the code and generates human-like comments based on its understanding.

Hardcoded Comment Generation: If you prefer, you can also generate hardcoded comments by uncommenting a specific line of code in the actionPerformed method of the GenerateComment class. This allows you to customize the comment generation process according to your specific needs.

### Functionality
`The GenerateComment class` provides the main functionality of generating comments for selected code. It retrieves the selected code from the editor, generates a comment for it using a comment generator service, and inserts the generated comment at the current caret position in the editor.
If there's an exception during the process, such as being unable to connect to the AI service, it shows an error message.

`The CommentGeneratorService class` is responsible for generating comments for given code snippets using the OpenAI API.
Obtain an API key from OpenAI. Replace <Enter API KEY of Open API> with your actual API key in the API_KEY field of the CommentGeneratorService class.
Use the generateComment method to generate a comment for a given code snippet.
The method takes the code snippet as input and returns the generated comment.
If successful, the method returns the generated comment. If an error occurs during the API request, it throws an exception.

#### OpenAiService
``` Java
String requestBody = "{\"model\":\"text-davinci-003\",\"prompt\":\"" + selectedCode + "\",\"max_tokens\":50}";
        URL url = new URL(GPT_ENDPOINT);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization",  "Bearer "+API_KEY);
        con.setDoOutput(true);
```
#### How to Use 
#####  To use the CommentGeneratorService in your project:

Add the CommentGeneratorService class to your project's source code.
Replace <Enter API KEY of Open API> with your actual OpenAI API key in the API_KEY field.
Call the generateComment method with the code snippet for which you want to generate a comment.
Handle any exceptions that may occur during the comment generation process.

#### Troubleshooting
If you encounter any issues with the service, such as errors during comment generation or connectivity problems with the OpenAI API, please follow these steps:

Check your internet connection to ensure it's stable.
Verify that the OpenAI API key is correct and properly configured.
If the issue persists, consider contacting OpenAI support for assistance.

#### Dependencies
Java SE Development Kit (JDK) 8 or higher

OpenAI API key

