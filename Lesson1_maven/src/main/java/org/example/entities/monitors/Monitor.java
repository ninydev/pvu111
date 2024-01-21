package org.example.entities.monitors;

import lombok.Data;

@Data
public class Monitor implements MonitorTypeInterface, PowerTypeInterface
{
    MonitorType inputType;
    PowerType powerType;
}
