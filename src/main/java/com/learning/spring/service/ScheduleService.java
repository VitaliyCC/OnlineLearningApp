package com.learning.spring.service;

import com.learning.spring.models.Department;
import com.learning.spring.models.Group;
import com.learning.spring.models.Schedule;
import com.learning.spring.util.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class ScheduleService {
    @Value("${URL}")
    String url;

    private final JsonReader reader;

    public ScheduleService(JsonReader reader) {
        this.reader = reader;
    }

    public List<Department> getAllDepartments() throws IOException {

        List<Department> resultList = new LinkedList<>();

        JSONArray departments = reader.readJson(url+"/departments");

        for (int i = 0; i < departments.length(); i++) {
            JSONObject dep = (JSONObject) departments.get(i);

            Department department = new Department(
                    dep.getInt("code"),
                    dep.getString("shortN"),
                    dep.getString("name"),
                    dep.getString("chief"));
            resultList.add(department);
        }
        return resultList;
    }

    public List<Group> getAllGroupsByDepartment(Integer depId){
        List<Group> resultList = new LinkedList<>();

        JSONArray groups = null;
        try {
            groups = reader.readJson(url+"/groups/"+depId);

            for (int i = 0; i < groups.length(); i++) {
                JSONObject temp = (JSONObject) groups.get(i);

                Group group = new Group(
                        (Integer) temp.get("dep"),
                        (Integer) temp.get("course"),
                        (Integer) temp.get("strm"),
                        (Integer) temp.get("grp"),
                        (String) temp.get("depShort"),
                        (String) temp.get("name"),
                        (String) temp.get("nameP"));
                resultList.add(group);
            }
        } catch (IOException e) {
           resultList.add(new Group(0,0,0,0,"","",""));
        }

        return resultList;
    }

    public List<Schedule> findScheduleByGroup(Integer depId, Integer course,
                                              Integer stream, Integer groupCode,
                                              Integer subGroupCode) throws IOException {
        List<Schedule> resultList = new LinkedList<>();
        String subGroupCodeNew = subGroupCode == 0 ? "" : String.valueOf(subGroupCode);

        JSONArray schedules = reader.readJson(url+"/schedule/" + depId +
                "/" + course +
                "/" + stream +
                "/" + groupCode +
                "/" + subGroupCodeNew);

        for (int i = 0; i < schedules.length(); i++) {
            JSONObject temp = (JSONObject) schedules.get(i);
           Schedule schedule = new Schedule(
                    temp.getString("time"),
                    temp.getString("teacher"),
                    temp.getString("discipline"),
                    temp.getString("classroom"),
                    temp.getString("group"),
                    (Boolean) temp.get("lecture")
            );
            resultList.add(schedule);
        }
        return resultList;
    }
}
