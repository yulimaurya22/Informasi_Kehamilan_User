package com.yulia.informasikehamilan.MODEL;

import java.util.List;



/**
 * dikarenakan format response dari API itu ada status, message dan result
 * jadinya kita ga bisa manggil langsung ke Model_berat atau Model_jam, harus lewat Model_response
 * variable result nanti isinya bisa Model_jam atau yg lainnya, biar bisa gitu katanya gemini harus pake <Object>
 * kalau ga lewat sini jadi nya error Expected BEGIN_ARRAY but was BEGIN_OBJECT
 * **/

public class Model_response {

    private String status;
    private String message;
    private List<Object> result;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(List<Object> result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Object> getResult() {
        return result;
    }


}
