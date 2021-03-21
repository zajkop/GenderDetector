:books: Silent Eight Homework Description :books:  
Gender Detector application provides two HTTP endpoints:
1. Detect gender by given name input and detection option - available at /detect-gender
- Name input is a single string which can contains many name tokens like "Patryk Martyna Adam"
- Detector option is a string equals to SINGLE(to detect gender based only on first token in name input) or ALL(to detect gender based on all tokens in name input)
- Both inputs are validated - both inputs can't be empty and should contain only letters and spaces, in case of detector option validation - only possible options are SINGLE/ALL
2. Get all tokens for each gender - available at /all-genders-tokens
- Male and Female tokens available in system are combined and provided to client

:books: Usage :books: 
1. Detect gender examples:
- send get request to /detect-gender?name=Patryk Martyna Adam&option=SINGLE to detect gender based on first name token
- send get request to /detect-gender?name=Patryk Martyna Adam&option=ALL to detect gender based on all tokens
2. To get all tokens for each gender:
- send get request to /all-genders-tokens to retrieve tokens for each gender 
3. Available tokens:  
https://github.com/zajkop/GenderDetector/blob/master/src/main/resources/tokens/female-tokens.txt  
https://github.com/zajkop/GenderDetector/blob/master/src/main/resources/tokens/male-tokens.txt

:hammer: Configuration :hammer:  
1. Build & Run application using Gradle:  
- gradlew build && java -jar build/libs/genderdetector-0.0.1-SNAPSHOT.jar  

2. Build docker image file:  
- docker build -t genderdetector .  

3. To run docker image on port 8080 type:  
- docker run -p 8080:8080 genderdetector  
