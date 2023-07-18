package com.paulosrlj.straypets.domain.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;


public class FileTypeValidator implements ConstraintValidator<FileTypeValidation, List<MultipartFile>> {

    private List<String> allowedContentTypes;

    @Override
    public void initialize(FileTypeValidation constraint) {
        this.allowedContentTypes = Arrays.asList(constraint.allowed());
    }

    @Override
    public boolean isValid(List<MultipartFile> multipartFiles, ConstraintValidatorContext constraintValidatorContext) {
        var valid = true;

        for(MultipartFile file : multipartFiles) {
            if (!this.allowedContentTypes.contains(file.getContentType()))
                return false;
        }

        return valid;
    }

}
