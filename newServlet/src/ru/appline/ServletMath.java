package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import ru.appline.logic.Dataset;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/math")
public class ServletMath extends HttpServlet {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        StringBuffer sb = new StringBuffer();
        PrintWriter pw = response.getWriter();
        Dataset dataset = new Dataset();

        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }

        JsonObject jObj = gson.fromJson(String.valueOf(sb), JsonObject.class);

        double num1 = jObj.get("a").getAsDouble();
        double num2 = jObj.get("b").getAsDouble();
        String math = jObj.get("math").getAsString();

        switch (math) {
            case "+":
                dataset.result = num1 + num2;
                pw.print(gson.toJson(dataset));
                break;
            case "-":
                dataset.result = num1 - num2;
                pw.print(gson.toJson(dataset));
                break;
            case "*":
                dataset.result = num1 * num2;
                pw.print(gson.toJson(dataset));
                break;
            case "/":
                dataset.result = num1 / num2;
                pw.print(gson.toJson(dataset));
                break;
            default:
                dataset.code = "INTERNAL_ERROR";
                dataset.message = "request failed";
                pw.print(gson.toJson(dataset));
                break;
        }
    }
}
//{
//    "a": 10,
//    "b": 5,
//    "math": "*"
//