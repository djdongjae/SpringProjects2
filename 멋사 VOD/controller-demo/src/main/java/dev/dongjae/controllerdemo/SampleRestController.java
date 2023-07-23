package dev.dongjae.controllerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("rest")
public class SampleRestController {
    private static final Logger logger = LoggerFactory.getLogger(SampleRestController.class);


    @GetMapping("sample-payload")
    public SamplePayload samplePayload() {
        return new SamplePayload("Dongjae Oh", 15, "Student");
    }

    @PostMapping("sample-payload")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void samplePayload(@RequestBody SamplePayload samplePayload) {
        logger.info(samplePayload.toString());
    }

    @PostMapping(
            value = "sample-multipart",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody SamplePayload sampleMultipartPost(
            @RequestParam("name") String name,
            @RequestParam("age") Integer age,
            @RequestParam("occupation") String occupation,
            @RequestParam("file")MultipartFile multipartFile
            ) {
        return new SamplePayload(name, age, occupation);
    }

    @GetMapping(value = "/sample-image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] sampleImage() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/static/img.png");
        return inputStream.readAllBytes();
    }

}
