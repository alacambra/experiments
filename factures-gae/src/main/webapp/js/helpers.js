var Helper = function() {
    this.clone = function(original) {
        var cloned = Object.create(original);
        for(var a in original){
            cloned[a] = original[a];
        }
        return cloned;
    };
    
    this.convertDateToTimeStamp = function(formatedDate){
        var parts = formatedDate.split('-');
        return new Date(parts[2], parts[1]-1, parts[0]).getTime();
    };
};


